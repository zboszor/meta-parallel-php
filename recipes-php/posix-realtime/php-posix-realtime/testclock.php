<?php

$time = posix_clock_gettime(PSXRT_CLK_MONOTONIC);
echo "time = $time\n";

var_dump(posix_clock_gettime(PSXRT_CLK_MONOTONIC_RAW, PSXRT_AS_TIMESPEC));

?>
