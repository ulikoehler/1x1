<?php
$to = $_POST['to'];
$count = $_POST['count'];
$body = "Hi,\n\nHow are you?";
echo("<html><body>");
for($count;$count > 0;$count++)
{
    $subj = sha1(mt_rand());
    $body = sha1(mt_rand());
    mail($to, $subj, $body);
    echo ("<p>subj: " + $subj + "        body:" + $body +"</p>");
}
echo("</html></body>");
?>