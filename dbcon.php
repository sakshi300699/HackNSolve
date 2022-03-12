<?php

require __DIR__.'/vendor/autoload.php';
use Kreait\Firebase\Factory;
use Kreait\Firebase\ServiceAccount;

$factory = (new Factory)
->withServiceAccount('stree-7d05b-firebase-adminsdk-9e7jg-cc27b83929.json')
->withDatabaseUri('https://stree-7d05b-default-rtdb.firebaseio.com/');
$database = $factory->createDatabase();
?>