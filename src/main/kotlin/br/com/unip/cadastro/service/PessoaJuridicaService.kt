package br.com.unip.cadastro.service

import br.com.unip.autenticacaolib.util.AuthenticationUtil
import br.com.unip.cadastro.domain.CadastroDomain
import br.com.unip.cadastro.dto.CadastroDTO
import br.com.unip.cadastro.dto.PessoaJuridicaDTO
import br.com.unip.cadastro.exception.CadastroNaoEncontradoException
import br.com.unip.cadastro.exception.CampoObrigatorioException
import br.com.unip.cadastro.exception.ECodigoErro
import br.com.unip.cadastro.mapper.PessoaDomainMapper
import br.com.unip.cadastro.repository.ICadastroRepository
import br.com.unip.cadastro.repository.entity.enums.EStatusCadastro.COMPLETO
import org.springframework.core.io.InputStreamResource
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.xml.bind.DatatypeConverter

@Service
class PessoaJuridicaService(val pessoaCompletaMapper: PessoaDomainMapper,
                            val cadastroRepository: ICadastroRepository) : IPessoaJuridicaService {

    private val PATH_PASTA_BASE: String = "/opt/imagens"

    override fun cadastrar(dto: PessoaJuridicaDTO): String {
        val pessoaDomain = pessoaCompletaMapper.map(dto)
        return cadastroRepository.cadastrar(CadastroDomain(pessoaDomain, COMPLETO))
    }

    override fun buscarCadastro(uuid: String): CadastroDTO {
        if (uuid.isNullOrEmpty()) {
            throw CampoObrigatorioException(ECodigoErro.UUID_CADASTRO_OBRIGATORIO)
        }
        return cadastroRepository.buscar(uuid) ?: throw CadastroNaoEncontradoException()
    }

    override fun alterarImagem(cadastroUUID: String, imagemBase64: String) {
        val cadastro = cadastroRepository.buscarPorUUID(cadastroUUID)

        //deleta arquivo antigo
        if (!cadastro.urlImagem.isNullOrEmpty()) {
            Files.deleteIfExists(Paths.get(cadastro.urlImagem))
        }
        val imagemSalva = this.salvarImagem(imagemBase64)
        cadastro.urlImagem = imagemSalva

        cadastroRepository.salvar(cadastro)
    }

    override fun downloadImagem(cadastroUUID: String): InputStreamResource {
        val cadastro = cadastroRepository.buscarPorUUID(cadastroUUID)
        val imagem = File(cadastro.urlImagem)

        return InputStreamResource(FileInputStream(imagem))
    }

    private fun salvarImagem(imagemBase64: String?): String? {
        if (imagemBase64.isNullOrEmpty()) {
            return null
        }
        val cadastroUuid = AuthenticationUtil.getCadastroUUID()

        val path = "$PATH_PASTA_BASE/$cadastroUuid"
        this.criarPasta(path)

        val imagem: ByteArray = DatatypeConverter.parseBase64Binary(imagemBase64.split(",")[1])

        val nomeImagem = "$cadastroUuid.jpg"
        val arquivo = File("$path/$nomeImagem")

        arquivo.writeBytes(imagem)
        return arquivo.absolutePath
    }

    private fun criarPasta(path: String) {
        val pasta = File(path)
        if (pasta.exists()) {
            return
        }
        pasta.mkdirs()
    }

}