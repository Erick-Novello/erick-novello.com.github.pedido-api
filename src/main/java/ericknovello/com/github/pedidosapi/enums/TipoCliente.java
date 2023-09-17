package ericknovello.com.github.pedidosapi.enums;

public enum TipoCliente {
    
    PESSOAFISICA(1,  "Pessoa fisica"),
    PESSOAJURIDICA(2,  "Pessoa juridica");

        private Integer codigo;
        private String descricao;

        private TipoCliente(Integer codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public static TipoCliente toEnum(Integer codigo){
            if(codigo == null)
                return null;

                for (TipoCliente x : TipoCliente.values()){
                    if(codigo.equals(x.getCodigo())){
                        return x;
                    }
                }

                throw new IllegalArgumentException("Id de Tipo do Cliente invalido: " + codigo);
        }

        public Integer getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

}
