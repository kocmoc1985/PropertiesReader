/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supplementary;

/**
 *
 * @author KOCMOC
 */
public class SpecsFile {

    private boolean exist;
    private String path;

    public SpecsFile(boolean exist, String path) {
        this.exist = exist;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean exist() {
        return exist;
    }
    
    
}
