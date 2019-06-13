int item; //item {[1,'match'], [2, 'paper'], [3, 'tobacco']}

int va=1; //representa o agente
int va=1; // representa o agente que vai colocar os ingredientes na mesa
int vsm=0; // representa o Smoker que possui o Match 
int vsp=0; // representa o Smoker que possui o Paper
int vst=0; // representa o Smoker que possui Tobacco

//Representa o processo do agente que vai colocar os ingredientes na mesa
void process_agent(){
	while(true){
		<await (va == 1) va = va - 1;>
		item = random(1, 3);
		if(item==1){
			coloca_tabaco_mesa();
			coloca_papel_mesa();
			<vsm = vsm + 1;> //habilita o processo smoker_match para rodar
		}else if (item==2){
			coloca_tabaco_mesa();
			coloca_match_mesa();
			<vsp = vsp + 1;> //habilita o processo smoker_paper para rodar
		}else{
			coloca_match_mesa();
			coloca_papel_mesa();				
			<vst = vst + 1;> //habilita o processo smoker_tobacco para rodar
		}
	}
}

//representa o processo smoker que possui o tobacco
void process_smoker1(){
	while(true){
		<await (vst == 1) vst = vst - 1;>	
		pega_match();
		pega_paper();
		fuma();
		<va = va + 1;> //habilita o processo agente 
	}
}

//representa o processo smoker que possui o paper
void process_smoker2(){
	while(true){
		<await (vsp == 1) vsp = vsp - 1;>
		pega_match();
		pega_tobaco();
		fuma();
		<va = va + 1;> //habilita o processo agente 
	}
}

//representa o processo smoker que possui o match
void process_smoker3(){
	while(true){
		<await (vsm == 1) vsm = vsm - 1;> 
		pega_tobaco();
		pega_paper();
		fuma();
		<va = va + 1;> //habilita o processo agente 
	}
}

//processo principal que dispara os demais processos
void main(){
	co
		<process_agent();> //
		<process_smoker1();> //
		<process_smoker2();> //
		<process_smoker3();>
	oc
}
