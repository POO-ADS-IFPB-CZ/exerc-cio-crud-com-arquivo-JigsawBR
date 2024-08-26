import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDao {

    private File arquivo;

    public PessoaDao(){
        this.arquivo= new File("Pessoas.ser");

        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();
            }catch (IOException e){
                System.out.println("Falha ao criar arquivo Pessoas.ser.");
            }
        }

    }

    //Pegando lista de pessoas
    public Set<Pessoa> getPessoas(){
        if(arquivo.length() > 0){
            try {
                FileInputStream inputArquivo = new FileInputStream(arquivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputArquivo);
                Set<Pessoa> pessoas = (Set<Pessoa>) objectInputStream.readObject();
                return pessoas;
            }catch (FileNotFoundException e){
                System.out.println("Arquivo não localizado.");
            }catch (IOException e){
                System.out.println("Falha ao escrever no arquivo.");
            }catch (ClassNotFoundException e){
                System.out.println("Falha ao ler arquivo.");
            }
        }
        return new HashSet<>();
    }

    //Salvando pessoas na lista
    public boolean salvarPessoa(Pessoa pessoa){
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.add(pessoa)){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            }catch (FileNotFoundException e){
                System.out.println("Arquivo não encontrado.");
            }catch (IOException e){
                System.out.println("Falha ao escrever no arquivo.");
            }
        }
        return false;
    }

    //Deletando uam pessoa da lista
    public boolean deletarPessoa(Pessoa pessoa){
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.remove(pessoa)){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            }catch (FileNotFoundException e){
                System.out.println("Arquivo não encontrado.");
            }catch (IOException e){
                System.out.println("Falha ao escrever no arquivo.");
            }
        }
        return false;
    }


    //Deletando pessoa por email
    public boolean deletarPorEmail(String email){
        Set<Pessoa> pessoas = getPessoas();
        for(Pessoa pessoa : pessoas){
            if(pessoa.getEmail().equals(email)){
                System.out.println("Pessoa deletada com sucesso.");
                deletarPessoa(pessoa);
                return true;
            }
        }
        return false;
    }

}