package br.com.unip.cadastro.exception

enum class ECodigoErro {

    ERRO_INESPERADO("000"),
    TOKEN_EXPIRADO("001"),
    CAMPO_OBRIGATORIO("002"),
    ACESSO_NEGADO("003"),
    PARAMETRO_INVALIDO("004"),
    TOKEN_INVALIDO("005"),
    FORMATO_DATA_INVALIDA("006"),
    CAMPO_DEVE_SER_NUMERICO("007"),
    CNPJ_INVALIDO("008"),
    CNPJ_OBRIGATORIO("009"),
    CPF_INVALIDO("010"),
    CPF_OBRIGATORIO("011"),
    UUID_CADASTRO_OBRIGATORIO("012"),
    NOME_OBRIGATORIO("013"),
    RAZAO_SOCIAL_OBRIGATORIO("014"),
    DATA_DEVE_SER_RETROATIVA("015"),
    CAMPO_TELEFONE_DEVE_SER_NUMERICO("016"),
    CADASTRO_NAO_ENCONTRADO("017"),
    CAMPO_TAMANHO_LIMITE_EXCEDENTE("018"),
    CAMPO_TELEFONE_EXCEDE_TAMANHO_LIMITE("019"),
    CAMPO_BAIRRO_OBRIGATORIO("020"),
    CAMPO_CIDADE_OBRIGATORIO("021"),
    CAMPO_CEP_OBRIGATORIO("022"),
    CAMPO_LOGRADOURO_OBRIGATORIO("023"),
    CAMPO_NUMERO_DEVE_SER_NUMERICO("024"),
    CAMPO_CEP_INVALIDO("025"),
    CAMPO_CEP_DEVE_SER_NUMERICO("026"),
    CAMPO_ESTADO_OBRIGATORIO("027"),
    DOCUMENTO_JA_CADASTRADO("028"),
    CONEXAO_RECUSADA("029"),
    USUARIO_NAO_POSSUI_CADASTRO("030"),
    CAMPO_DATA_ESPECIAL_OBRIGATORIO("031"),
    CAMPO_DATA_ESPECIAL_DEVE_SER_FUTURO("032"),
    CAMPO_ABERTURA_OBRIGATORIO("033"),
    CAMPO_FECHAMENTO_OBRIGATORIO("034"),
    DATA_DEVE_SER_FUTURA("035"),
    HORARIO_DIFERENCIADO_NAO_ENCONTRADO("036"),
    DIA_DA_SEMANA_INVALIDO("037"),
    CAMPO_DIA_SEMANA_OBRIGATORIO("038"),
    FORMATO_HORARIO_INVALIDO("039"),
    HORARIO_INVALIDO("040");

    val codigo: String

    constructor(codigo: String) {
        this.codigo = codigo
    }
}