<?php

	include ('pdf/mpdf.php');

	$nome = $_GET['nome'];
	$idade = $_GET['idade'];
	$tipoDiabetes = $_GET['tipo'];

	$sintomas = $_GET['sintomas'];
	$medicamentos = $_GET['medicamentos'];
	$linhasSintomas = $_GET['linhasSintomas'];
	$linhasMedicamentos = $_GET['linhasMedicamentos'];

	$linhasGlicemia = $_GET['linhasGlicemia'];
	$indicesGlicemicos = $_GET['glicemia'];

	if(empty($sintomas)){
		$sintomas = "[NENHUM DADO INSERIDO]";
	} else{
		$parteSintomas = explode(';', $sintomas);
		$sintomas = "";	
		for($contLS = 0; $contLS <= $linhasSintomas; $contLS++){
			$sintomas = $sintomas."{$parteSintomas[$contLS]}<br>";
		}
		$sintomas = str_replace(",","",$sintomas);
		$sintomas = str_replace("[","",$sintomas);
		$sintomas = str_replace("]","",$sintomas);
	}

	if(empty($medicamentos)){
		$medicamentos = "[NENHUM DADO INSERIDO]";
	} else{
		$parteMedicamentos = explode(';', $medicamentos);
		$medicamentos = "";
		for($contLM = 0; $contLM <= $linhasMedicamentos; $contLM++){
			$medicamentos = $medicamentos."{$parteMedicamentos[$contLM]}<br>";
		}
		$medicamentos = str_replace(",","",$medicamentos);
		$medicamentos = str_replace("[","",$medicamentos);
		$medicamentos = str_replace("]","",$medicamentos);
	}

	if(empty($indicesGlicemicos)){
		$indicesGlicemicos = "[NENHUM DADO INSERIDO]";
	} else{
		$parteGlicemia = explode(';', $indicesGlicemicos);
		$indicesGlicemicos = "";
		for($contLG = 0; $contLG <= $linhasGlicemia; $contLG++){
			$indicesGlicemicos = $indicesGlicemicos."{$parteGlicemia[$contLG]}<br>";
		}
		$indicesGlicemicos = str_replace(",","",$indicesGlicemicos);
		$indicesGlicemicos = str_replace("[","",$indicesGlicemicos);
		$indicesGlicemicos = str_replace("]","",$indicesGlicemicos);
	}

	date_default_timezone_set('America/Sao_Paulo');
	$dateTime = date('d/m/Y H:i', time());	

	$pagina = 
		"<html>
			<head>
				<meta charset=\"UTF-8\">
			</head>
			<body>
				<img src=\"logo.jpg\" width=\"100\" height=\"100\">
				<img src=\"logo.jpg\" width=\"100\" height=\"100\" style=\"margin: -100 0px 0px 576px\">
				<p style=\"margin: -90px 0px 0px 0px; font-size: 13px; text-align: center;\">Este relatório é gerado a partir de informações<br> fornecidas pelo usuário. 
				O mesmo tem a finalidade de ser <br>encaminhado a doutores ou especialistas na doença.<br><br>RELATÓRIO EMITIDO EM ".$dateTime."</p><br>
				<hr style=\"margin-top: 10px;\">
				<h2 style=\"text-align: center;\">RELATÓRIO DIABETIC LIFE</h2><br><br>
				<b>NOME:</b> ".$nome."<br>
				<b>IDADE:</b> ".$idade."<br>
				<b>TIPO:</b> ".$tipoDiabetes."<br>
				<hr style=\"margin-top: 10px;\"><br><br>
				<h3>SINTOMAS APRESENTADOS</h3>
				<p>".$sintomas."</p>
				<hr style=\"margin-top: 10px;\"><br><br>
				<h3>MEDICAMENTOS USADOS</h3>
				<p>".$medicamentos."</p>
				<hr style=\"margin-top: 10px;\"><br><br>
				<h3>ÍNDICES GLICÊMICOS EM mg/dL</h3>
				<p>".$indicesGlicemicos."</p>
			</body>
		</html>
		";

	$arquivo = "Relatorio ".$dateTime.".pdf";

	$mpdf = new mPDF();

	$mpdf->WriteHTML($pagina);
	
	$mpdf->Output($arquivo, 'D');

	// I - Abre no navegador
	// F - Salva o arquivo no servido
	// D - Salva o arquivo no computador do usuário

?>