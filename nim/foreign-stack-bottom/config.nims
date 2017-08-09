import strutils, ospaths

let executable = $CurDir / "main".toExe
let nimLibDir = selfExe().parentDir.parentDir / "lib"

task build_lib, "Build the static library":
  # always GC
  --define: fulldebug

  --threads: on
  --tlsEmulation: off
  --app:staticlib
  --noMain
  --header

  setCommand "c", "lib.nim"

task build_main, "Build the executable":
  let platformFlags = if defined(windows): "" else: "-ldl"
  let cmd = "c++ --std=c++11 main.cpp -I$# -pthread -L. -llib $# -o $#" %
   [nimLibDir, platformFlags, executable]
  exec cmd

task run, "Build and run the executable":
  selfExec "build_lib"
  selfExec "build_main"
  exec executable
