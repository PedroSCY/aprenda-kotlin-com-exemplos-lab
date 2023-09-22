// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String, val email: String) {
    
    override fun equals(other: Any?) = other is Usuario && other.email == this.email  
    
    override fun hashCode(): Int {
        var result = email.hashCode()
        return result * 31
    }
}

data class ConteudoEducacional(val id: Int, var nome: String, val nivel: Nivel, val duracao: Int = 60) {
    override fun equals(other: Any?) = other is ConteudoEducacional && other.id == this.id  
    
    override fun hashCode(): Int {
        var result = id.hashCode()
        return result * 31
    }
}


data class Formacao(val nome: String, val conteudos: MutableSet<ConteudoEducacional> = mutableSetOf()) {

    val inscritos = mutableSetOf<Usuario>()
    
    fun adicionarConteudo(CE: ConteudoEducacional) {
		if(conteudos.add(CE)) {
			println("$nome | [SUCCESS] Conteudo Educacional adicionado. (${CE.nome})")
        } else {
            println("$nome |  [ERROR]  Conteudo Educacional duplicado. (${CE.nome})")
        }
    }
    
    fun removerConteudo(CE: Int) {
		for(conteudo in conteudos){
            if(conteudo.id == CE){
				conteudos.remove(conteudo);
                println("$nome | [SUCCESS] Conteudo Educacional removido. (${conteudo.nome})")
                return;
            } 
        }
        println("$nome |  [ERROR]  Conteudo Educacional não encontrado. (ID: $CE)")
    }
    
    
    fun matricular(usuario: Usuario) {
        if(inscritos.add(usuario)) {
            println("$nome | [SUCCESS] Usuario cadastrado. (${usuario.email})");
        } else {
            println("$nome |  [ERROR]  Email já cadastrado. (${usuario.email})");
    	}
    }
    
    fun cancelarMatricula(email: String){
		val user = inscritos.filter {it.email.equals(email)};
        if(!user.isEmpty()){
			inscritos.remove(user[0]);
            println("$nome | [SUCCESS] Usuario removido. (${user[0].email})")
        }else{
            println("$nome |  [ERROR]  Usuario não encontrado. (${user[0].email})")
        } 
    }
    
    fun listarInscritos() {
        
        println("\n${nome.toUpperCase()}\n|||||||INSCRITOS|||||||")
		inscritos.map{println("${it.nome} ${it.email}")}
        println("|||||||||||||||||||||||\n")
    }
    
    fun listarConteudos() {
        println("\n${nome.toUpperCase()}\n|||||||CONTEUDOS|||||||")
		conteudos.map{println("ID: ${it.id} CE: ${it.nome}")}
        println("|||||||||||||||||||||||\n")
    }
}

fun main() {
    
    // Criandos 3 objetos Usuario
    val user1 = Usuario("Pedro", "user1@gmail.com")
    val user2 = Usuario("Lucas", "user2@gmail.com")
    val user3 = Usuario("Joao", "user1@gmail.com")
    
    // Criando 4 objetos ConteudoEducacional
    val CE1 = ConteudoEducacional(1, "Hello Kotlin", Nivel.BASICO ,30)
    val CE2 = ConteudoEducacional(2, "Hello JAVA", Nivel.INTERMEDIARIO)
    val CE3 = ConteudoEducacional(3, "Hello JAVA", Nivel.INTERMEDIARIO,20)
    val CE4 = ConteudoEducacional(4, "Hello Python", Nivel.AVANCADO, 90)
    
    // Criando 2 objetos Formacao
    val FJK = Formacao("Formacao Java-Kotlin", mutableSetOf(CE1, CE2, CE3, CE4))
    val FJP = Formacao("Formacao Java-Python", mutableSetOf(CE2, CE3, CE4))
    
    // Matriculando 2 usuarios com sucesso na formacao FJK
    FJK.matricular(user1);
    FJK.matricular(user2);
    
    // Tentando matricular usuario com um email já cadastrado na formacao FJK
   	FJK.matricular(user3);
   	
    // Exibindo a lista de inscritos na formacao FJK
    FJK.listarInscritos();
    
    // Cancelando a matricula de um Usuario na formacao FJK
    FJK.cancelarMatricula(user1.email);
    
    // Exibindo a lista de inscritos na formacao FJK
    FJK.listarInscritos();
    
    // Criando e Adicionando conteudo na formacao FJK
    val CE5 = ConteudoEducacional(5, "Spring Boot", Nivel.AVANCADO, 120)
    FJK.adicionarConteudo(CE5)
    
    // Listando conteudos da formacao FJK
    FJK.listarConteudos()
    
    // Removendo conteudo da formacao FJK
    FJK.removerConteudo(2)
    
    // Listando conteudos da formacao FJK
    FJK.listarConteudos()
    
    // Tentando remover conteudo que não existe na formacao FJK
    FJK.removerConteudo(2)
    
    // Tentando adicionar conteudo duplicado na formacao FJP
    FJP.adicionarConteudo(CE4)
    
    // Listando conteudos da formacao FJP
    FJP.listarConteudos()
}



// A DIO possui Formacoes incríveis que têm como objetivo oferecer um conjunto de ConteudosEducacionais voltados para uma stack tecnológica específica,
// preparando profissionais de TI para o mercado de trabalho. Formacoes possuem algumas características importantes, como nome, nivel e seus respectivos
// conteudosEducacionais. Além disso, tais experiências educacionais têm um comportamento relevante ao nosso domínio, definido pela capacidade de
// matricular um ou mais Alunos.