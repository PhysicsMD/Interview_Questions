<script>
    export let boxX, boxY
</script>

<div class="coordinateTab">
    X: {Math.round(boxX)} , Y: {Math.round(boxY)}
</div>

<style>
    .coordinateTab {
        z-index: 2;
        position: absolute;
	    color: white;
        background-color: #999;
        border-radius: 50px;
        width: auto;
        padding: 1px 7px 2px 7px;
        bottom: 10px;
        right: 10px;
    }
</style>
