package br.com.unip.cadastro.domain.campos.pessoa.completo

import br.com.unip.cadastro.domain.campos.CampoObrigatorio
import br.com.unip.cadastro.domain.campos.DataPassada
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoNumericoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.DataPassadaException
import br.com.unip.cadastro.exception.ECodigoErro
import java.time.LocalDate

class DataNascimentoCompleto : ICampo<LocalDate?> {

    private val data: LocalDate?

    constructor(data: String?) {
        try {
            this.data = DataPassada(CampoObrigatorio(data).get()).get()
        } catch (e: DataPassadaException) {
            throw CampoNumericoException("Data de nascimento deve ser uma data passada", ECodigoErro.CAD016)
        } catch (e: CampoObrigatorioException) {
            throw CampoObrigatorioException("Data de nascimento é obrigatória", ECodigoErro.CAD017)
        }
    }

    override fun get(): LocalDate? {
        return data
    }
}