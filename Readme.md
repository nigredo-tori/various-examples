# sbt-unidoc evicted dependencies example

To reproduce, run:
```
> compile
> unidoc
```

Result:
```
sbt:root> compile
[info] Updating foo...
[info] Updating ...
[info] Done updating.
[info] Updating bar...
[info] Done updating.
[info] Done updating.
[warn] There may be incompatibilities among your library dependencies; run 'evicted' to see detailed eviction warnings.
[info] Compiling 1 Scala source to /tmp/sbt-test/bar/target/scala-2.12/classes ...
[info] Done compiling.
[success] Total time: 5 s, completed Feb 7, 2019 10:29:14 PM
sbt:root> unidoc
[info] Main Scala API documentation to /tmp/sbt-test/target/scala-2.12/unidoc...
[error] /tmp/sbt-test/bar/src/main/scala/Bar.scala:5:6: value evalMap is not a member of cats.effect.Resource[cats.effect.IO,Unit]
[error] possible cause: maybe a semicolon is missing before `value evalMap'?
[error]     .evalMap(IO.pure)
[error]      ^
[error] /tmp/sbt-test/bar/src/main/scala/Bar.scala:5:17: missing argument list for method pure in object IO
[error] Unapplied methods are only converted to functions when a function type is expected.
[error] You can make this conversion explicit by writing `pure _` or `pure(_)` instead of `pure`.
[error]     .evalMap(IO.pure)
[error]                 ^
[info] No documentation generated with unsuccessful compiler run
[error] two errors found
[error] (Scalaunidoc / doc) Scaladoc generation failed
[error] Total time: 1 s, completed Feb 7, 2019 10:29:16 PM
```

Warning: this seems to depend on the project folder path. For me, the example fails when located in `/tmp/sbt-test`.
