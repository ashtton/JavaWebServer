# WebServer
Welcome to the **Java Webserver** repository.\
This is a lightweight java library allowing you to create html & css webservers in java.\

There will most likely be more updates to come to this library in the future.

## How to use
**Main Class**
```java
public class Test extends Website {
    public Test() {
        setPort(256);
        enable();

        addReplacer("banana", new BananaReplacer());
        addPage(new Page("/", "html/index.html"));
    }
}
```

**Replacer Class**
```java
public class BananaReplacer implements Replacer {
    public String replace(String s) {
        return String.valueOf(new Random().nextInt());
    }
}
```

**index.html**\
This is placed in the html/index.html path in the resources folder.\
You can put any html, javascript, or css that you wish in here.
```html
<!doctype html>
<html>

    <head>
        <title>Test website</title>
    </head>

    <body>
        <h1 style="color: yellow;">hola amigo, como estas? {banana}</h1>
    </body>

</html>
```
