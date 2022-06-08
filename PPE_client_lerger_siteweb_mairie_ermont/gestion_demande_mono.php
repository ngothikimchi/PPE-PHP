<?php
    require_once("header.php");   

	if(!isset($_SESSION['role']) 
	|| $_SESSION['role'] != 2 
	|| !isset($_SESSION['idServiceEmploye']) 
	|| $_SESSION['idServiceEmploye'] != 1 )
{
	require_once("vue/vue_connexion.php");     
	return;
}

    require_once("vue/vue_all_demandes_mono.php");
    if (isset($_GET['action']) && isset($_GET['idDemande'])) 
		{
			$action = $_GET['action'];
			$idDemande = $_GET['idDemande'];

			if($action == 'valider')
				ValiderDemandeMono($idDemande);
			if($action == 'refuser')
				RefuserDemandeMono($idDemande);
        }

?>
<?php
require_once("footer.php");
?>
