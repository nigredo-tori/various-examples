{.pragma: api, exportc, cdecl.}

proc c_printf(frmt: cstring): cint {.
  importc: "printf", header: "<stdio.h>", varargs, discardable.}

proc flat*(cs: cstring) {.api.} =
  setupForeignThreadGc()

  let s1 = $cs
  cprintf("flat before GC: \"%s\"\L", s1)
  GC_fullCollect()
  var s2 = $cs
  s2.setLen(0)
  cprintf("flat after GC: \"%s\"\L", s1)

  teardownForeignThreadGc()

proc noTeardownImpl(cs: cstring) =
  let s1 = $cs
  cprintf("noTeardown before GC: \"%s\"\L", s1)
  GC_fullCollect()
  var s2 = $cs
  s2.setLen(0)
  cprintf("noTeardown after GC: \"%s\"\L", s1)

proc noTeardown*(cs: cstring) {.api.} =
  setupForeignThreadGc()
  noTeardownImpl(cs)
