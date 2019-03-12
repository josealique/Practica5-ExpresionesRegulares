import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    private String texto;
    private Pattern pattern;
    private Matcher matcher;


    public Find(String s) {
        this.texto = s;

    }

    public boolean match(String stringPat) {
        if (stringPat.equals("")) return false;
        stringPat = metaCaracteres(stringPat);
        pattern = Pattern.compile(stringPat);
        matcher = pattern.matcher(texto);
        return matcher.find();
    }

    private String metaCaracteres(String stringPat) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < stringPat.length(); i++) {
            char c = stringPat.charAt(i);
            if (c == '@') {
                c = stringPat.replace(c, '\\').charAt(i);
                resultado.append(c);
                char c1 = stringPat.charAt(++i);
                if (c1 == '@') {
                    resultado.append("@");
                } else {
                    resultado.append(c1);
                }
            } else if (c == '?') {
                resultado.append("[a-zA-Z]");
            } else if (c == '%') {
                c = '^';
                resultado.append(c);
                char c1 = stringPat.charAt(i + 1);
                if (c1 == '%') {
                    resultado.append("\\%");
                    i++;
                }
            } else if (c == '$' && i < stringPat.length() - 1) {
                resultado.append("\\$");
            } else {
                resultado.append(c);
            }

        }
        System.out.println(resultado);
        return resultado.toString();
    }
}