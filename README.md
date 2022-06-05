# cryptic-list
Encrypt and decrypt entries just for fun.

## Generate Jar file

In the root folder of the project run:

```shell
> mvn clean package
```

## Manually testing the Jar file

The Jar file will be created inside the `target` folder. You can run the Jar like this:

```shell
> java -jar target/cryptic-list-jar-with-dependencies.jar <option>
```

where `option` could be:
- "-a" for adding a new registry
- "-ap" for adding a new registry with an auto-generated password
- "-l" to list all of the registries
- "-r" to read the data of a record. We need to pass the entry id first
