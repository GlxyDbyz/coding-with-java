package org.dbyz.design_pattern._3Adapter;

/**
 * 中文翻译
 *
 * @ClassName: ChinessInterpreter
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public class ChinessInterpreter implements Interpreter {
	private ForeignScientist scientist;

	@Override
	public void translate() {// 不创造信息，只是起到转换的作用
		System.out.println(scientist.say().replaceAll("Science is the future",
				"科学就是未来"));
	}

	public ChinessInterpreter() {
		super();
	}

	public ChinessInterpreter(ForeignScientist scientist) {
		super();
		this.scientist = scientist;
	}

	public ForeignScientist getScientist() {
		return scientist;
	}

	public void setScientist(ForeignScientist scientist) {
		this.scientist = scientist;
	}
}
