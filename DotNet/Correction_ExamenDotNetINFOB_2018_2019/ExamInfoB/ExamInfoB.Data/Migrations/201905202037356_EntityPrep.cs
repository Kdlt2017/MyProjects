namespace ExamInfoB.Data.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class EntityPrep : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Entreprises",
                c => new
                    {
                        IdEntreprise = c.Int(nullable: false, identity: true),
                        ChiffreAffaire = c.Int(nullable: false),
                        Adresse_CodePostal = c.Int(nullable: false),
                        Adresse_Ville = c.String(),
                        Adresse_Region = c.String(),
                        DateFondation = c.DateTime(nullable: false),
                        Description = c.String(),
                        Effectif = c.Int(nullable: false),
                        NomEntreprise = c.String(),
                        Secteur = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.IdEntreprise);
            
            CreateTable(
                "dbo.Offres",
                c => new
                    {
                        IdOffre = c.Int(nullable: false, identity: true),
                        DatePublication = c.DateTime(nullable: false),
                        Description = c.String(),
                        TitreOffre = c.String(),
                        TypeContrat = c.String(),
                        EntrepriseKey = c.Int(),
                    })
                .PrimaryKey(t => t.IdOffre)
                .ForeignKey("dbo.Entreprises", t => t.EntrepriseKey)
                .Index(t => t.EntrepriseKey);
            
            CreateTable(
                "dbo.Postulants",
                c => new
                    {
                        IdPostulant = c.Int(nullable: false, identity: true),
                        DateNaissance = c.DateTime(nullable: false),
                        Email = c.String(),
                        Nom = c.String(),
                        Prenom = c.String(),
                    })
                .PrimaryKey(t => t.IdPostulant);
            
            CreateTable(
                "dbo.Candidature",
                c => new
                    {
                        Postulant = c.Int(nullable: false),
                        Offre = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Postulant, t.Offre })
                .ForeignKey("dbo.Offres", t => t.Postulant, cascadeDelete: true)
                .ForeignKey("dbo.Postulants", t => t.Offre, cascadeDelete: true)
                .Index(t => t.Postulant)
                .Index(t => t.Offre);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Candidature", "Offre", "dbo.Postulants");
            DropForeignKey("dbo.Candidature", "Postulant", "dbo.Offres");
            DropForeignKey("dbo.Offres", "EntrepriseKey", "dbo.Entreprises");
            DropIndex("dbo.Candidature", new[] { "Offre" });
            DropIndex("dbo.Candidature", new[] { "Postulant" });
            DropIndex("dbo.Offres", new[] { "EntrepriseKey" });
            DropTable("dbo.Candidature");
            DropTable("dbo.Postulants");
            DropTable("dbo.Offres");
            DropTable("dbo.Entreprises");
        }
    }
}
