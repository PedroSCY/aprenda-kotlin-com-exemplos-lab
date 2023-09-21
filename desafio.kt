// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String, val idade: Int, val email: String) {
    override fun equals(other: Any?) = other is Usuario && other.email == this.email    
}

data class ConteudoEducacional(val id: Int, var nome: String, val nivel: Nivel, val duracao: Int = 60) {
    override fun equals(other: Any?) = other is ConteudoEducacional && other.id == this.id  
}

data class Formacao(val nome: String, var conteudos: MutableSet<ConteudoEducacional>) {

    val inscritos = mutableSetOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        inscritos.add(usuario);
    }
    
    fun cancelarMatricula(email: String){
		val user = inscritos.filter {it.email.equals(email)}[0];
        inscritos.remove(user);
    }
}

fun main() {
    
    val user1 = Usuario("Pedro", 17, "user1@gmail.com")
    val user2 = Usuario("Lucas", 22, "user2@gmail.com")
    val user3 = Usuario("Joao", 20, "user1@gmail.com")
    
    val CE1 = ConteudoEducacional(1, "Hello Kotlin", Nivel.BASICO ,30)
    val CE2 = ConteudoEducacional(2, "Hello JAVA", Nivel.INTERMEDIARIO)
    val CE3 = ConteudoEducacional(3, "Hello JAVA", Nivel.INTERMEDIARIO,20)
    val CE4 = ConteudoEducacional(4, "Hello Python", Nivel.AVANCADO, 90)
    
    var FJK = Formacao("Formacao Java-Kotlin", mutableSetOf(CE1, CE2, CE3, CE4))
    var FJP = Formacao("Formacao Java-Python", mutableSetOf(CE2, CE3, CE4))
    
    FJK.matricular(user1);
    FJK.matricular(user3);
    
    println(FJK.inscritos);
    
    FJK.cancelarMatricula(user1.email);
    
    println(FJK.inscritos);
    
//     println(FJK.toString());
//     println(FJK.conteudos);
//     println(FJK.inscritos);
    
}



// A DIO possui Formacoes incríveis que têm como objetivo oferecer um conjunto de ConteudosEducacionais voltados para uma stack tecnológica específica,
// preparando profissionais de TI para o mercado de trabalho. Formacoes possuem algumas características importantes, como nome, nivel e seus respectivos
// conteudosEducacionais. Além disso, tais experiências educacionais têm um comportamento relevante ao nosso domínio, definido pela capacidade de
// matricular um ou mais Alunos.
