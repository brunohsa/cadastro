package br.com.unip.cadastro.domain.campos.pessoa.pf

import br.com.unip.cadastro.domain.campos.CampoOpcional
import br.com.unip.cadastro.domain.campos.DataPassada
import br.com.unip.cadastro.domain.campos.ICampo
import br.com.unip.cadastro.exception.CampoNumericoException
import br.com.unip.cadastro.exception.DataPassadaException
import br.com.unip.cadastro.exception.ECodigoErro
import java.time.LocalDate

class DataNascimento : ICampo<LocalDate?> {

    private val data: LocalDate?

    constructor(data: String?) {
        try {
            this.data = DataPassada(CampoOpcional(data).get()).get()
        } catch (e: DataPassadaException) {
            throw DataPassadaException("Data de nascimento deve ser uma data passada", ECodigoErro.DATA_DEVE_SER_RETROATIVA)
        }
    }

    override fun get(): LocalDate? {
        return data
    }
}