#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

#include <php-screw.h>
#include "my_screw.h"

#define PM9SCREW	"\tPM9SCREW\t"
#define PM9SCREW_LEN	10

int main(int argc, char **argv) {
	int f;
	off_t len, pos;
	int newlen, newlen2;
	char *old, *new;
	char newname[256];
	char header[PM9SCREW_LEN];
	struct stat stat_old;

	if (argc == 1) {
		printf("usage: %s file.php\n", argv[0]);
		printf("the file.php should have been encrypted with php-screw\n");
		return 0;
	}

	f = open(argv[1], O_RDONLY);
	if (read(f, header, PM9SCREW_LEN) != PM9SCREW_LEN ||
		strncmp(header, PM9SCREW, PM9SCREW_LEN)) {
		printf("invalid file\n");
		return 1;
	}

	fstat(f, &stat_old);
	len = stat_old.st_size - PM9SCREW_LEN;
	old = malloc(len);

	pos = 0;
	while (pos < len) {
		int ret;
		ret = read(f, old + pos, len - pos);
		if (ret >= 0)
			pos += ret;
		else {
			printf("read error: %d '%s'\n", errno, strerror(errno));
			return 1;
		}
	}

	close(f);

	new = php_unscrew(pm9screw_mycryptkey, sizeof(pm9screw_mycryptkey) / 2, old, len, &newlen);
	if (new == NULL) {
		printf("php_unscrew out of memory\n");
		return 1;
	}

	printf("php_unscrew returned valid buffer of size %d\n", newlen);

	sprintf(newname, "%s.unscrew", argv[1]);

	f = open(newname, O_CREAT | O_TRUNC | O_WRONLY, stat_old.st_mode & (S_ISUID|S_ISGID|S_ISVTX|S_IRWXU|S_IRWXG|S_IRWXO));
	if (f <= 0) {
		printf("file open error for %s: %d '%s'\n", newname, errno, strerror(errno));
		return 1;
	}

	fchmod(f, 0644);

	newlen2 = write(f, new, newlen);
	if (newlen2 != newlen)
		printf("write failed, returned %d, expected %d\n", newlen2, newlen);

	close(f);

	return 0;
}
