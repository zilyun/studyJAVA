package ex19_02_Serialization_error;

// 직렬화 가능 클래스를 만드는 예
// - implements java.io.Serializable 없는 클래스 - 직렬화 안됨
// 실행 순서 : ObjectOutputExample2.java -> ObjectInputExample2.java
public class GoodsStock implements java.io.Serializable { // 재고 정보 클래스
	/**
	 * 시리얼 통신을 하는 클래스의 버전을 표시하는 것이다. (직렬화 버전의 고유값)
	 * 직렬화/역직렬화를 수행 할 때, 내부적으로 특정 버전에 맞는지 판단
	 */
	private static final long serialVersionUID = -1476753696293931044L;
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private String code;
	private int num;

	public GoodsStock(String code, int num) {
		this.code = code;
		this.num = num;
	}

	void addStock(int num) {
		this.num = num;
	}

	int subtractStock(int num) throws Exception {
		if (this.num < num)
			throw new Exception("재고가 부족합니다.");
		this.num -= num;
		return num;
	}

	@Override
	public String toString() {
		return "상품코드:" + code + "\t상품수량:" + num;
	}

}
