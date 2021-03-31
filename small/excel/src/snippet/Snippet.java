package snippet;

import java.util.ArrayList;
import java.util.List;

public class Snippet {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 5; i++)
			list.add(i + "");
		list.remove(2);
		System.out.println();
	}
}
