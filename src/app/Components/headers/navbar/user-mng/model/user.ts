export interface User{
    id:string;
    firstName:string;
    lastName:string;
    email:string;
    mobileNumber:string;
    accessItem:string;
    roleName:string;
    manager:string;
    partner:string;
    vendorTemplateAccess:string;
    dashboardAccess:string;
    masterRepoAccess:string;
    status:boolean;
    createdOn:Date;
}


export interface Role{
    role:string;
}

export interface Manager{
    name:string;
}

export interface Partner{
    name:string;
}