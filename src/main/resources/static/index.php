<!DOCTYPE html lang="en">
<html lang="en">
    <head>  
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Autopoly</title> 
        <link rel="icon" type="image/png" href="favicon.png">
        <link rel="stylesheet" href="main.css"> 
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    </head>
    <body>
        <?php include("sidebar.html"); ?>
		<!-- INFO O GRACZACH ITD. W SIDEBARZE -->
		
        <div id="img_loader">
			<!-- ZAŁADOWAĆ PÓŹNIEJ GRAFIKĘ WYKORZYSTYWANĄ W ANIMACJACH I RYSOWANĄ W INTERWAŁACH ŻEBY NIE ŚCIĄGAŁO CO CHWILĘ NA NOWO -->
        </div>
        <div id="GameScreen">
            <canvas id="main-canvas" width="640" height="640"></canvas>
        </div>
        
        <script type="text/javascript" src="game.js"></script>
    </body>
</html>