
package regex.tietorakenteet;

public class Solmu<T> {
    private T data;
    private Solmu seuraava;
        
    public Solmu(T data) {
        this.data = data;
        this.seuraava = null;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the seuraava
     */
    public Solmu getSeuraava() {
        return seuraava;
    }

    /**
     * @param seuraava the seuraava to set
     */
    public void setSeuraava(Solmu seuraava) {
        this.seuraava = seuraava;
    }
    
    
}
