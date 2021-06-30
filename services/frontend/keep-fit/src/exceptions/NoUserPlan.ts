class NoUserPlan extends Error{
    constructor(message: string) {
        super(message);

        Object.setPrototypeOf(this, NoUserPlan.prototype);
    }
}

export default NoUserPlan;
