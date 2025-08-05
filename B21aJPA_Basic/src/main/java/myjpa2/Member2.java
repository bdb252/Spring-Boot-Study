package myjpa2;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="JpaMember2")
public class Member2 {

	/*
	시퀀스에 대한 세부설정을 할 수 있다.
	@SequenceGenerator(
		name : Java코드에서 연결하기 위한 이름
		sequenceName : Oracle에 생성되는 시퀀스명
		initialValue : 초기값
		allocationSize : 증가치
	)
	 */
	@Id
	@SequenceGenerator(
		name= "mySequence01",
		sequenceName = "JpaMember2_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
	
	/*
	앞에서 생성된 시퀀스의 별칭을 통해 접근한다. 즉 Java에서는 mySequence01을 사용하고,
	oracle에서는 JpaMember2_SEQ로 시퀀스가 생성된다.
	 */
	@GeneratedValue(generator = "mySequence01")
	private Long id;
	
	/*
	@Access : 데이터에 접근하기 위한 방법을 명시한다.
		AccessType.FIELD: 필드명. 즉 변수명을 통해 접근한다.
			별도의 명시가 없는 경우 Default값이 된다.
		AccessType.PROPERTY : 프로퍼티. 즉 메서드를 통해 접근한다.
			따라서 아래에는 getter/setter를 생성한다.
	 */
	@Access(AccessType.FIELD)
	private String username;

	@Access(AccessType.PROPERTY)
	private String password;
	
	/*
	영속 대상에서 제외되는 항목 설정. 즉 해당 멤버변수를 통해서는 컬럼이 생성되지 않는다.
	어노테이션 혹은 키워드를 통해 항목을 설정한다.
	 */
	@Transient
	private long timestamp1;
	transient private long timestamp2;
	
	public Member2() {};
	public Member2(String username, String password) {
		super();
		this.username=username;
		this.password=password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
