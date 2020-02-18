### Sardine warning example

Sardine logs a warning if a response, that doesn't have explicit `Content-Length`, is closed.

To reproduce:

0. Make sure you have SBT installed.
1. Run the example via `sbt run`.
2. Observe the output:

    ```
    Feb 18, 2020 10:40:38 AM com.github.sardine.impl.io.HttpMethodReleaseInputStream close
    WARNING: Abort connection for response HttpResponseProxy{HTTP/1.1 200 OK [Date: Tue, 18 Feb 2020 03:40:38 GMT, Transfer-encoding: chunked] ResponseEntityProxy{[Chunked: true]}}
    This is the response
    ```
