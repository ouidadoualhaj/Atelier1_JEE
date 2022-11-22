package ma.fstt.entities;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LigneCmd {

	private int num_ligne;
	private int qte_cmd;
	private int code_prod;
	private int num_cmd;
	
	
	public LigneCmd() {
		super();
	}

	public LigneCmd(int num_ligne, int qte_cmd, int code_prod, int num_cmd) {
		super();
		this.num_ligne = num_ligne;
		this.qte_cmd = qte_cmd;
		this.code_prod = code_prod;
		this.num_cmd = num_cmd;
	}

	public int getNum_ligne() {
		return num_ligne;
	}

	public void setNum_ligne(int num_ligne) {
		this.num_ligne = num_ligne;
	}

	public int getQte_cmd() {
		return qte_cmd;
	}

	public void setQte_cmd(int qte_cmd) {
		this.qte_cmd = qte_cmd;
	}

	public int getCode_prod() {
		return code_prod;
	}

	public void setCode_prod(int code_prod) {
		this.code_prod = code_prod;
	}

	public int getNum_cmd() {
		return num_cmd;
	}

	public void setNum_cmd(int num_cmd) {
		this.num_cmd = num_cmd;
	}

	@Override
	public String toString() {
		return "LigneCmd [num_ligne=" + num_ligne + ", qte_cmd=" + qte_cmd + ", code_prod=" + code_prod + ", num_cmd="
				+ num_cmd + "]";
	}
	
	
}
