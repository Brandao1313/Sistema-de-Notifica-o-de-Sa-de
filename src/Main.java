import ui.MenuCLI;

/**
 * Sistema de Notificação de Agravos de Saúde Pública
 * 
 * Este sistema permite o registro, consulta e geração de relatórios
 * de notificações de agravos de saúde pública, incluindo:
 * - Hanseníase
 * - Tuberculose
 * - Malária
 * 
 * @author Sistema de Vigilância em Saúde
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Notificação de Agravos de Saúde Pública...\n");
        
        MenuCLI menu = new MenuCLI();
        menu.exibir();
        menu.fechar();
    }
}
