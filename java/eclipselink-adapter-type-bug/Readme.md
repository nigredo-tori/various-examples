# Json adapter bug example.

Custom `XmlAdapter` can result in invalid JSON when used with `@XmlSchemaType`.

### Running the example

```shell
$ mvn test
```

### Result

```json
{
   "noAdapter" : 111111111111111111111111111.11111111111111111,
   "customAdapter" : custom:111111111111111111111111111.11111111111111111,
   "customAdapterNoType" : "custom:111111111111111111111111111.11111111111111111"
}
```
