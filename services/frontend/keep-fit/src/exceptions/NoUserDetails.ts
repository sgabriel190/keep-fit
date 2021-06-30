class NoUserDetails extends Error{
    constructor(message: string) {
        super(message);

        Object.setPrototypeOf(this, NoUserDetails.prototype);
    }
}

export default NoUserDetails;
