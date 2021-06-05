package demo01;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 天真无邪
 * @createdate 2020年3月21日下午3:56:56
 * @Desciption 寻月
 *
 *             tcp通信的客户端:向服务器发送连接请求,给服务器发送数据,读取的服务器返回的数据 
 *             表示客户端的类:java.net.Socket
 *             此类实现客户端套接字（也可以就叫“套接字”）。套接字是两台机器间通信的端点
 * 
 **/
public class TcpSocket2 {

	public static void main(String[] args) {
		try {
			// 绑定服务器的ip与端口号
			Socket sc = new Socket("127.0.0.1", 8888);
			// 获取输出流向服务器发送数据
			OutputStream os = sc.getOutputStream();
			os.write("向服务器发送数据,我是客户端2".getBytes());
			// 使用输入流获取服务器返回的数据
			InputStream is = sc.getInputStream();
			byte[] bytes = new byte[1024];
			int read = is.read(bytes);
			System.out.println(new String(bytes, 0, read));
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
