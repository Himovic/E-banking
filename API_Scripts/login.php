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
	$Email = $_GET['Email'];
	$Password1= $_GET['Password'];
    $sql="SELECT idClient,Nom,Prenom,DateN,NumCompte,Email,DateEn,Solde,Adresse FROM personne JOIN client WHERE personne.idPersonne=client.idPersonne AND personne.Email='$Email' AND personne.Password='$Password1'";
    $req=mysqli_query($conn,$sql);
    $data = mysqli_fetch_assoc($req);
    header('Content-Type: application/json');
    json_encode($data);
    print json_encode($data);
    //print $Email;
    //print $Password1;
?>