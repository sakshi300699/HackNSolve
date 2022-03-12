<?php
session_start();
include("dbcon.php");
if(isset($_POST['patient'])){
    require_once('./config.php');
    $email=$_POST['email'];
    $u_name= $_POST['u_name'];
    $pass= $_POST['password'];
    $uid= uniqid($u_name);
    $occupation= "patient";
    $securepass= crypt($pass,$u_name);
    $postData = [
        'userid' => $uid,
        'username' => $u_name,
        'password' => $securepass,
        'email' => $email,
        'occupation' => $occupation
    ];
    $postRef = $database->getReference('patient')->push($postData);
    if($postRef){
        $_SESSION['status'] = "successfull";
        $sql= "INSERT INTO `feminine`.`users` (`userid`, `username`, `password`, `email`, `occupation`) VALUES ('$uid', '$u_name', '$securepass', '$email', '$occupation');";
    
        if($con->query($sql) == true){
          echo "<br><br><br><h3 style='margin-left:400px;margin-top:20px;color:green' >You have successfully registered<a href='login.php'>Go back to Login Page</a></h3>";
          
      }
      else{
         echo "ERROR: $con->error";
      }
      $con->close();
    }
    else{
        $_SESSION['status'] = "unsuccessfull";
        header('Location: signup.php');
    }

}

if(isset($_POST['doctor'])){
    require_once('./config.php');
    $email=$_POST['email'];
    $u_name= $_POST['u_name'];
    $pass= $_POST['password'];
    $uid= uniqid($u_name);
    $occupation= "doctor";
    $securepass= crypt($pass,$u_name);
    $postData = [
        'userid' => $uid,
        'username' => $u_name,
        'password' => $securepass,
        'email' => $email,
        'occupation' => $occupation
    ];
    $postRef = $database->getReference('doctor')->push($postData);
    if($postRef){
        $_SESSION['status'] = "successfull";
        $sql= "INSERT INTO `feminine`.`users` (`userid`, `username`, `password`, `email`, `occupation`) VALUES ('$uid', '$u_name', '$securepass', '$email', '$occupation');";
    
        if($con->query($sql) == true){
          echo "<br><br><br><h3 style='margin-left:400px;margin-top:20px;color:green' >You have successfully registered<a href='login.php'>Go back to Login Page</a></h3>";
          
      }
      else{
         echo "ERROR: $con->error";
      }
      $con->close();
    }
    else{
        $_SESSION['status'] = "unsuccessfull";
        header('Location: signup.php');
    }

}

?>

<?php
// session_start();
// include("dbcon.php");

// if(isset($_POST['patient'])){
//     $postData = [
//         'oxysat' => 949.69,
//         'pressure' => 67777969,
//         'temp' => 'grgdgr'
//     ];
//     $postRef = $database->getReference('patient')->push($postData);
//     if($postRef){
//         $_SESSION['status'] = "successfull";
//         header('Location: index.php');
//     }
//     else{
//         $_SESSION['status'] = "unsuccessfull";
//     }
// }
?>