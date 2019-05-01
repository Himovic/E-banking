<?php 
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "mbanking";
	// Create connection
	$conn = mysqli_connect($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
    die("Connection failed: " .mysqli_connect_error());
	}
	$idClient=$_GET['idClient'];
	$sql="SELECT idClient,Nom,Prenom,DateN,Email,NumCompte,DateEn,Solde,Adresse FROM personne JOIN client WHERE personne.idPersonne=client.idPersonne AND client.idClient='$idClient'";
    $req=mysqli_query($conn,$sql);
    $data = mysqli_fetch_assoc($req);
    header('Content-Type: application/json');
    json_encode($data);
    print json_encode($data);
?>