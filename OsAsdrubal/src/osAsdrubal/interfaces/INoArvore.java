package osAsdrubal.interfaces;
import java.util.List;

/**
 * Interface do componente NoArvore.
 * @author Marco Aur�lio
 */
public interface INoArvore {

	public void addFilho(int id, String texto, String[] legendasInferiores);
	public INoArvore encontreNoPorId(int id);
	public void setPai(INoArvore pai);
	public int getId();
	public int getIdPai();
	public String getTexto();
	public boolean temPai();
	public List<INoArvore> getFilhos();
	public String[] getLegInf();

}
