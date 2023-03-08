package gui;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;



public class RandomTunisiaFactsController {

    @FXML
    private Label factLabel;

    @FXML
    private Button generateButton;
    

    private String[] facts = {
        "Religion : La religion majoritaire est l'islam, avec environ 99% de la population tunisienne étant musulmane. Il y a également une petite minorité chrétienne et juive.",
        "Cuisine : La cuisine tunisienne est influencée par la cuisine méditerranéenne et nord-africaine, avec des plats comme le couscous, le brik, la chakchouka, le lablabi, le tajine et le mechouia.",
        "Musique : La musique tunisienne est diverse, allant des styles traditionnels tels que le malouf et le stambeli, aux styles modernes comme le rap, le rock et la pop.",
        "Artisanat : La Tunisie est connue pour son artisanat, notamment pour la poterie, les tapis et les bijoux en argent.",
        "Fêtes et événements : La Tunisie a de nombreux événements culturels tout au long de l'année, comme le festival de Carthage, le festival international de la médina de Tunis, le festival de la mer de Bizerte et le festival du Sahara de Douz.",
        "Sites touristiques : La Tunisie est célèbre pour ses sites historiques, tels que les ruines romaines de Carthage et les sites archéologiques de Dougga et de Bulla Regia. La Tunisie est également connue pour ses plages, notamment celles de Djerba et de Hammamet.",
        "Sport : Le football est le sport le plus populaire en Tunisie, avec l'équipe nationale ayant participé à plusieurs coupes du monde de football. La Tunisie est également connue pour son festival annuel de course de dromadaires à Douz.",
        "Littérature : La Tunisie a une longue tradition littéraire, remontant à l'époque romaine et arabe. Des écrivains tunisiens célèbres incluent Tahar Ben Jelloun, Amina Saïd et Moncef Marzouki.",
        "Mode : La Tunisie est devenue une destination populaire pour la production de vêtements de mode en raison de sa main-d'œuvre bon marché et de sa position géographique stratégique.",
        "Langue : La langue officielle est l'arabe, mais le français est largement parlé et enseigné dans les écoles."
    };
    @FXML
    private Button Retour;
    
    

    @FXML
    private void generateFact() {
        int index = (int) (Math.random() * facts.length);
        String fact = facts[index];
        factLabel.setText(fact);
    }
    
    
    
    
    
    
    
}
