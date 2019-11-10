package br.com.unip.cardapio.domain.campos.completo

import br.com.unip.cardapio.domain.campos.CampoObrigatorio
import br.com.unip.cardapio.domain.campos.DataPassada
import br.com.unip.cardapio.domain.campos.ICampo
import br.com.unip.cardapio.exception.CampoNumericoException
import br.com.unip.cardapio.exception.CampoObrigatorioException
import br.com.unip.cardapio.exception.DataPassadaException
import br.com.unip.cardapio.exception.ECodigoErro
import java.time.LocalDate

class DataFundacaoCompleto : ICampo<LocalDate?> {

    val data: LocalDate?

    constructor(data: String?) {
        try {
            this.data = DataPassada(CampoObrigatorio(data).get()).get()
        } catch (e: DataPassadaException) {
            throw CampoNumericoException("Data de fundação deve ser uma data passada", ECodigoErro.CAD014)
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException("Data de fundação  é obrigatória", ECodigoErro.CAD015)
        }
    }

    override fun get(): LocalDate? {
        return data
    }
}