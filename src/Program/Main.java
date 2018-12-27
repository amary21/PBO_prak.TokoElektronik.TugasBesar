/*
 * Copyrigth (c) 2018, All right reserved
 * Written by tauf21, email : taufik.amary@gmail.com
 * Github : tauf21
 */

package Program;

/**
 *
 * @author Taufik Amaryansyah
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Form_Loading loading = new Form_Loading();
        Form_MenuUtama menu =new Form_MenuUtama();
        loading.setVisible(true);
        
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(80);
                loading.LoadBar.setValue(i);
                if (i==100) {
                    loading.setVisible(false);
                    menu.setVisible(true);
                }
            }
        } catch (Exception e) {
        }
    }

}
