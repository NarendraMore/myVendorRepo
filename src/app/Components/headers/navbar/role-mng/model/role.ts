export interface Role{
    id:string;
    roleName:string;
    numOfUsers:string;
    descriptions:string;
    checked:boolean;
    accessItem:string;
    vendorTemplateAccess:string[];
    dashboardAccess:string[];
    masterRepoAccess:string[];
    status:boolean;
    createdOn:Date;
}