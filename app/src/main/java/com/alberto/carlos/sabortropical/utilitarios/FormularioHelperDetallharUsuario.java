package com.alberto.carlos.sabortropical.utilitarios;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.alberto.carlos.sabortropical.Entidades.Usuario;
import com.alberto.carlos.sabortropical.R;

public class FormularioHelperDetallharUsuario {

	private EditText nome;
	private EditText sobreNome;
	private EditText dataNascimento;
	private Spinner corPele;
	private Spinner corOlhos;
	private Spinner sexo;
	private EditText nomePai;
	private EditText nomeMae;
	private Spinner estadoCivil;
	private EditText cpf;
	private EditText identidade;
	private EditText email;
	private EditText senha;
	private Spinner nivelAcesso;
	private Usuario usuario;

	public FormularioHelperDetallharUsuario(Activity activity) {

  		this.nome = (EditText) activity.findViewById(R.id.campo_nome);

  		this.sobreNome = (EditText) activity.findViewById(R.id.campo_sobreNome);

		this.dataNascimento = (EditText) activity.findViewById(R.id.campo_dataNascimento);

		final String[] strCorPele = new String[]{"Branco","Moreno","Negro"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strCorPele);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.corPele = (Spinner) activity.findViewById(R.id.campo_corPele);
		this.corPele.setAdapter(adapter);

		final String[] strCorOlhos = new String[]{"Azul","Verde","Castanho Claro","Castanho Escuro"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strCorOlhos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.corOlhos = (Spinner) activity.findViewById(R.id.campo_corOlhos);
		this.corOlhos.setAdapter(adapter);

		final String[] strSexo = new String[]{"Feminino","Masculino","Outros"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strSexo);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.sexo = (Spinner) activity.findViewById(R.id.campo_sexo);
		this.sexo.setAdapter(adapter);

		this.nomePai = (EditText) activity.findViewById(R.id.campo_nomePai);

		this.nomeMae = (EditText) activity.findViewById(R.id.campo_nomeMae);

		final String[] strEstadoCivil = new String[]{"Solteiro","Casado","Viúvo"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strEstadoCivil);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.estadoCivil = (Spinner) activity.findViewById(R.id.campo_estadoCivil);
		this.estadoCivil.setAdapter(adapter);

		this.cpf = (EditText) activity.findViewById(R.id.campo_cpf);
		this.cpf.addTextChangedListener(Mask.insert("###.###.###-##", cpf));

		this.identidade = (EditText) activity.findViewById(R.id.campo_identidade);
		this.identidade.addTextChangedListener(Mask.insert("#.###-###", identidade));

		this.email = (EditText) activity.findViewById(R.id.campo_email);

		this.senha = (EditText) activity.findViewById(R.id.campo_senha);

		final String[] strNIvelAcesso = new String[]{"Usuário","Administrador"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strNIvelAcesso);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.nivelAcesso = (Spinner) activity.findViewById(R.id.campo_nivelAcesso);
		this.nivelAcesso.setAdapter(adapter);
		
		this.usuario = new Usuario();
	}
	
	public void colocaUsuarioNoFormulario(Usuario usuarioDetalhado) {

		this.usuario = usuarioDetalhado;

		this.nome.setText(this.usuario.getNome());
		this.sobreNome.setText(this.usuario.getSobreNome());
		this.dataNascimento.setText(this.usuario.getDataNascimento());
		this.corPele.setSelection(this.usuario.getCorPele());
		this.corOlhos.setSelection(this.usuario.getCorOlhos());
		this.sexo.setSelection(this.usuario.getSexo());
		this.nomePai.setText(this.usuario.getNomePai());
		this.nomeMae.setText(this.usuario.getNomeMae());
		this.estadoCivil.setSelection(this.usuario.getEstadoCivil());
		this.cpf.setText(this.usuario.getCpf());
		this.identidade.setText(this.usuario.getIdentidade());
		this.email.setText(this.usuario.getEmail());
		this.senha.setText(this.usuario.getSenha());
		this.nivelAcesso.setSelection(this.usuario.getNivel());
		
	}

}
