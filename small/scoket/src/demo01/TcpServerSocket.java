package demo01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 天真无邪
 * @createdate 2020年3月21日下午4:08:12
 * @Desciption 寻月 服务器端实现的步骤： 
 * 1.创建 ServerSocket,和指定的端口号 
 * 2.调用 accept方法获取请求客户端的对象的Socket 
 * 3.获取输入流,获取客户端发送的数据
 * 4.获取输出流向客户端发送数据 
 * 5.关闭 socket
 * 
 **/
public class TcpServerSocket {
	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			// 用于监听客户端 Socket 的连接请求
			 ss = new ServerSocket(8888);
			while (true) {
				// 每当客户端收到客户端请求时,服务器端将会产生一个对应的 Socket
				Socket socket = ss.accept();
				// 获取客户端的数据
				InputStream is = socket.getInputStream();
				byte[] bytes = new byte[1024];
				int len = is.read(bytes);
				System.out.println("服务器收到数据：" + new String(bytes, 0, len));
				// 向客户端发送数据
				OutputStream os = socket.getOutputStream();
				os.write("服务器向客户端返回数据".getBytes());
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
