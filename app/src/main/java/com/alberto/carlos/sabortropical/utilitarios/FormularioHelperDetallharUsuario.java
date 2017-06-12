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

		 EditText nome = (EditText) activity.findViewById(R.id.campo_nome);

		 EditText sobreNome = (EditText) activity.findViewById(R.id.campo_sobreNome);

		 EditText dataNascimento = (EditText) activity.findViewById(R.id.campo_dataNascimento);

		final String[] strCorPele = new String[]{"Branco","Moreno","Negro"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strCorPele);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		 Spinner corPele = (Spinner) activity.findViewById(R.id.campo_corPele);
		corPele.setAdapter(adapter);

		final String[] strCorOlhos = new String[]{"Azul","Verde","Castanho Claro","Castanho Escuro"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strCorOlhos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		 Spinner corOlhos = (Spinner) activity.findViewById(R.id.campo_corOlhos);
		corOlhos.setAdapter(adapter);

		final String[] strSexo = new String[]{"Feminino","Masculino","Outros"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strSexo);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		 Spinner sexo = (Spinner) activity.findViewById(R.id.campo_sexo);
		sexo.setAdapter(adapter);

		 EditText nomePai = (EditText) activity.findViewById(R.id.campo_nomePai);

		 EditText nomeMae = (EditText) activity.findViewById(R.id.campo_nomeMae);

		final String[] strEstadoCivil = new String[]{"Solteiro","Casado","Viúvo"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strEstadoCivil);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		 Spinner estadoCivil = (Spinner) activity.findViewById(R.id.campo_estadoCivil);
		estadoCivil.setAdapter(adapter);

		 EditText cpf = (EditText) activity.findViewById(R.id.campo_cpf);
		cpf.addTextChangedListener(Mask.insert("###.###.###-##", cpf));

		 EditText identidade = (EditText) activity.findViewById(R.id.campo_identidade);
		identidade.addTextChangedListener(Mask.insert("#.###-###", identidade));

		 EditText email = (EditText) activity.findViewById(R.id.campo_email);

		 EditText senha = (EditText) activity.findViewById(R.id.campo_senha);

		final String[] strNIvelAcesso = new String[]{"Usuário","Administrador"};
		adapter = new ArrayAdapter<String>
				(activity, android.R.layout.simple_spinner_dropdown_item, strNIvelAcesso);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		 Spinner nivelAcesso = (Spinner) activity.findViewById(R.id.campo_nivelAcesso);
		nivelAcesso.setAdapter(adapter);
		
		this.usuario = new Usuario();
	}
	
	public void colocaUsuarioNoFormulario(Usuario usuarioDetalhado) {

		this.usuario = usuarioDetalhado;
		
		nome.setText(usuarioDetalhado.getNome());
		
	}

}
