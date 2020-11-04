package br.com.unip.cadastro.domain.campos.horarios.horariodif

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.DataFutura
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.DataFuturaException
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_DATA_ESPECIAL_DEVE_SER_FUTURO
import br.com.unip.cadastro.exception.ECodigoErro.CAMPO_DATA_ESPECIAL_OBRIGATORIO
import java.time.LocalDate

class DataEspecial : ICampo<LocalDate> {

    private val campo: LocalDate

    constructor(campo: String?) {
        try {
            this.campo = DataFutura(CampoObrigatorio(campo).get()).get()!!
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException(CAMPO_DATA_ESPECIAL_OBRIGATORIO)
        } catch (e: DataFuturaException) {
            throw CampoObrigatorioException(CAMPO_DATA_ESPECIAL_DEVE_SER_FUTURO)
        }
    }

    override fun get(): LocalDate {
        return campo
    }
}