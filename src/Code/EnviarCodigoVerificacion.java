// cSpell:ignore verificacion
package Code;
import java.util.Random;


public class EnviarCodigoVerificacion extends EnviarCorreo {
        Random random = new Random();
        private String codigo = String.valueOf(random.nextInt(100_000, 999_999));
        private int intentos = 3;

        public EnviarCodigoVerificacion(String correoEnviar, String asunto, String mensaje) {
        super(correoEnviar, asunto, mensaje);
    }

        public int getIntentos() {
            return intentos;
        }


        public void setIntentos(int intentos) {
            this.intentos = intentos;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }
}
