<?php

if (!extension_loaded("Phar")) {
		echo "$argv[0] requires PHP extension $ext.\n";
		exit(1);
}

echo "Phar loaded";

foreach(array("SPL", "Reflection") as $ext) {
	if (!extension_loaded($ext)) {
		echo "$argv[0] requires PHP extension $ext.\n";
		exit(1);
	}
}

$hash_avail = Phar::getSupportedSignatures();

?>
