package br.com.x10d.presenca.view;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.x10d.presenca.R;
import br.com.x10d.presenca.util.AcaoSairDoAplicativo;
import br.com.x10d.presenca.util.MeuAlerta;
import br.com.x10d.presenca.webservice.RelatorioPercentualPresencaWS;

public class MenuSistemaActivity extends FragmentActivity {

	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private CustomDrawerAdapter customDrawerAdapter;
	private List<DrawerItem> listaDe_drawerItem;
	private Context context;
	private static final int REQUISICAO_PERMISSAO_ESCRITA = 333;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_sistema);

		context = MenuSistemaActivity.this;

		
		//FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
					//frameLayout.addView(new DashboardWebView().devolveWebView(context));

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		listaDe_drawerItem = new ArrayList<DrawerItem>();
		listaDe_drawerItem.add(new DrawerItem("Realizar Inscrição", R.drawable.book));
		listaDe_drawerItem.add(new DrawerItem("Gerar Código de Barras", R.drawable.impressora));
		listaDe_drawerItem.add(new DrawerItem("Registrar Presença", R.drawable.launcher_icon));
		listaDe_drawerItem.add(new DrawerItem("Gerar Relatórios", R.drawable.report));
		
		customDrawerAdapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, listaDe_drawerItem);

		listView = (ListView) findViewById(R.id.left_drawer);
		listView.setAdapter(customDrawerAdapter);
		listView.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		//actionBarDrawerToggle = new ActionBarDrawerToggle((Activity) context, drawerLayout, R.string.enviar, R.string.salvar);
		actionBarDrawerToggle = new ActionBarDrawerToggle((Activity) context, drawerLayout, R.drawable.ic_drawer, R.drawable.ic_drawer,R.drawable.ic_drawer);
		
		drawerLayout.setDrawerListener(new DrawerListener() {
			@Override
			public void onDrawerStateChanged(int arg0) {
			}
			@Override
			public void onDrawerSlide(View arg0, float arg1) {
			}
			@Override
			public void onDrawerOpened(View arg0) {
			}
			@Override
			public void onDrawerClosed(View arg0) {
			}
		});

		drawerLayout.setDrawerListener(actionBarDrawerToggle);
		if (savedInstanceState == null) {
			SelectItem(666);
		}

	}

	public void SelectItem(int possition) {

		if (possition != 666) {
			//Intent intent;
			switch (possition) {

			case 0: startActivity(new Intent(context, CadastroMembroActivity.class));	
				break;

			case 1:	startActivity(new Intent(context, GeraCodigoDeBarrasActivity.class));	
				break;
				
			case 2: startActivity(new Intent(context, ChamadaActivity.class));	
				break;
				
			case 3: criaListaComRelatorios();
				break;

			default:
				break;
			}
		}
	}

	private void criaListaComRelatorios() {
		
		ArrayList<String> lista = new ArrayList<String>();
						  //lista.add("Aproveitamento Por Dia");
						  lista.add("Percentual Presença");
							
		escolheApenasUmItemDaLista("Relatório", lista);
	}
	private void escolheApenasUmItemDaLista(String titulo, final ArrayList<String> lista) {

		ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.item_menu_geral, lista);

		AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
		builder1.setTitle(titulo);
		builder1.setSingleChoiceItems(arrayAdapter, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int posicao) {

				if (posicao == 0) {
					//new RelatorioAproveitamentoPorDiaWS(context).buscaRelatorio();
					
					
					if (Build.VERSION.SDK_INT >= 23) {
						if (permitiuEscrever()){
							new RelatorioPercentualPresencaWS(context).buscaRelatorio();
						}		
				    } 
					else {
						new RelatorioPercentualPresencaWS(context).buscaRelatorio();
				    }

					
				}
				//if (posicao == 1) {
					//new RelatorioPercentualPresencaWS(context).buscaRelatorio();
				//}				
				dialogInterface.dismiss();
			}
		});
		builder1.show();
	}
	
	private boolean permitiuEscrever(){
        
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            
        	requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUISICAO_PERMISSAO_ESCRITA);		      
 		
            return false;
        }
        return true;
    }
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return false;
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			SelectItem(position);
		}
	}

	@Override
	public void onBackPressed() {

		sairDoAplicativo();		
	}

	private void sairDoAplicativo() {
		
		AcaoSairDoAplicativo acaoSairAplicativo = new AcaoSairDoAplicativo(MenuSistemaActivity.this);
		
		new MeuAlerta("Sair", "Deseja sair do aplicativo?", context).meuAlertaSimNao(acaoSairAplicativo);
	}

}
