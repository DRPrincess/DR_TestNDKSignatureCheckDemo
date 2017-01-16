# DR_TestNDKSignatureCheckDemo
## Function
check signature of apk is legal use so file with help of NDK
 <br/>
(使用NDK 建立so文件完成对apk签名是否合法的验证)
- - -
## Principle
in the c++ code ,validate the current signature of apk whether is legal ,if yes,  return a key str , if no ,return error str <br/>

本Demo通过调用NDK层方法通过对签名的合法验证,返回一个与服务器通信的秘钥来在一定程度上防止二次打包

## links
My CSDN Blogs :[Android-安全-签名验证让二次打包变的更难](http://blog.csdn.net/qq_32452623/article/details/54351364)
