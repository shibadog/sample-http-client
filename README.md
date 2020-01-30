# おかしいぜ httpclient 3.0

## 概要

connection timeout と read timeout(so timeout?) を合計した時間よりも長い時間でタイムアウトエラーが発生した。

これを受け、なぜそんなことが起こりえるのかの理屈がわからないか調べてみたくなった。

## 動かしかた

```powershell
PS> ./mvnw package
PS> java -jar .\target\http-client-1.0.0-jar-with-dependencies.jar
```
