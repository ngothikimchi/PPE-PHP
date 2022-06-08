<h3 class="titre_page_admin"> Ajout un type événement d'enfant </h3>
<form method="post" action="" enctype="multipart/form-data">
<table class="style_table_inscrire">
    <tr class="style_line">
        <td>Code Type événement : </td>
        <td><input type="text" name="codeTypeEve"
        <?php if ($unTypeEveEnfant!=NULL) echo "disabled"; ?>
			value="<?php if ($unTypeEveEnfant!=NULL) echo $unTypeEveEnfant['codeTypeEve']; ?>"></td>
    </tr>

    <tr class="style_line">
        <td>Nom de type événement : </td>
        <td><input type="text" name="nomTypeEve"
			value="<?php if ($unTypeEveEnfant!=NULL) echo $unTypeEveEnfant['nomTypeEve']; ?>"></td>
    </tr>

    <tr class="style_line">
        <td>L'age minimale : </td>
        <td><input type="text" name="trancheAgeMin"
            <?php if ($unTypeEveEnfant!=NULL) echo "disabled"; ?>
			value="<?php if ($unTypeEveEnfant!=NULL) echo $unTypeEveEnfant['trancheAgeMin']; ?>"></td>
    </tr>

    <tr class="style_line">
        <td>L'age maximale : </td>
        <td><input type="text" name="trancheAgeMax"
        <?php if ($unTypeEveEnfant!=NULL) echo "disabled"; ?>
			value="<?php if ($unTypeEveEnfant!=NULL) echo $unTypeEveEnfant['trancheAgeMax']; ?>"></td>
    </tr>

    <tr class="style_line">
        <td>Accompagnant : </td>
        <td><input type="text" name="accompagnant"
        <?php if ($unTypeEveEnfant!=NULL) echo "disabled"; ?>
			value="<?php if ($unTypeEveEnfant!=NULL) echo $unTypeEveEnfant['accompagnant']; ?>"></td>
    </tr>
    
   
    <tr class="style_line">
        <td > <input style="width:50%;margin-left:20px;" type="reset" name="Annuler" value ="Annuler"> </td>
        <td> <input style="width:50%;margin-left:40px;" type="submit" <?php if($unTypeEveEnfant!=NULL) echo 'name ="Modifier" 
        value ="Modifier" '; else echo 'name="Valider" value ="Valider"'; ?> > 
		 </td>
	</tr>

    </table>
</form>

<?php
    $unControleur->setTable ("type_evenement_enfant");   
    if(isset($_POST['Modifier']))
    {   
        $where = array("codeTypeEve"=>$_GET['codeTypeEve']);
		$tab=array(
           "nomTypeEve"=>$_POST["nomTypeEve"]
            /* "trancheAgeMin"=>$_POST["trancheAgeMin"], 
            "trancheAgeMax"=>$_POST["trancheAgeMax"], 
            "accompagnant"=>$_POST["accompagnant"]  */
			);
		$unControleur->update ($tab, $where);
        
       // print_r($where);

       // print_r($tab);
		//redirect("gestion_all_type_Eve.php");
    }

    if (isset($_POST['Valider']))
    {

        $tab=array(
            "codeTypeEve"=>$_POST["codeTypeEve"],
            "nomTypeEve"=>$_POST["nomTypeEve"],
            "trancheAgeMin"=>$_POST["trancheAgeMin"], 
            "trancheAgeMax"=>$_POST["trancheAgeMax"], 
            "accompagnant"=>$_POST["accompagnant"] 
			);

		$unControleur->insert ($tab); 
        redirect("gestion_all_type_Eve.php");
    }



    
?>