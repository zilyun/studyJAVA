package ex19_03_Serialization_transient;

public class BBSItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private static int itemNum = 0; // 게시물의 수 - 정적 필드는 직렬화 대상이 되지 않습니다.
	private String writer;
	private transient String passwd; // 패스워드 - transient 키워드가 붙은
										// 인스턴스 필드는 직렬화 대상이 되지 않습니다.
	private String title;
	private String content;

	public BBSItem(String writer, String passwd, String title, String content) {
		this.writer = writer;
		this.passwd = passwd;
		this.title = title;
		this.content = content;
		itemNum++;
	}

	// 메소드는 직렬화 대상이 되지 않습니다.
	void modifyContent(String content, String passwd) {
		if (!passwd.equals(passwd))
			return;
		this.content = content;
	}

	// 메소드는 직렬화 대상이 되지 않습니다.
	public String toString() {
		return "전체 게시물의 수: " + itemNum + "\n글쓴이: " + writer + "\n패스워드: " + passwd + "\n제목: " + title + "\n내용: "
				+ content;
	}
}
